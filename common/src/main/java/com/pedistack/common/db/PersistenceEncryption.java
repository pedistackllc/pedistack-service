package com.pedistack.common.db;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Converter
public class PersistenceEncryption implements AttributeConverter<String, String> {

  private final transient Key key;
  private final transient Cipher cipher;

  public PersistenceEncryption(@Value("${database.password.secret.key}") String databaseSecretKey)
      throws NoSuchPaddingException, NoSuchAlgorithmException {
    final String algorithm = "AES";
    key = new SecretKeySpec(databaseSecretKey.getBytes(), algorithm);
    cipher = Cipher.getInstance(algorithm);
  }

  @Override
  public String convertToDatabaseColumn(final String data) {
    try {
      cipher.init(Cipher.ENCRYPT_MODE, key);
      if (data != null) {
        byte[] b = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(b);
      } else {
        return null;
      }
    } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public String convertToEntityAttribute(String data) {
    try {
      cipher.init(Cipher.DECRYPT_MODE, key);
      if (data != null) {
        return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
      } else {
        return null;
      }
    } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
      throw new IllegalStateException(e);
    }
  }
}
