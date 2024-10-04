package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaAccountEntityDao extends BaseDao<SocialMediaAccountEntity> {

  Optional<SocialMediaAccountEntity> findByUser_Id(String userIdentifier) throws PedistackException;

  Optional<SocialMediaAccountEntity> findByUser_ClientId(String clientId) throws PedistackException;

  Optional<SocialMediaAccountEntity> findByUser_MobileNumber(String mobileNumber)
      throws PedistackException;

  Optional<SocialMediaAccountEntity> findByUser_EmailAddress(String emailAddress)
      throws PedistackException;

  Optional<SocialMediaAccountEntity> findByUser_Username(String username) throws PedistackException;

  Optional<SocialMediaAccountEntity> findByTwitterAccountUsername(String twitterAccountUsername)
      throws PedistackException;

  Optional<SocialMediaAccountEntity> findByFacebookAccountUsername(String facebookUsername)
      throws PedistackException;

  Optional<SocialMediaAccountEntity> findByInstagramAccountUsername(String instagramAccountUsername)
      throws PedistackException;

  Optional<SocialMediaAccountEntity> findByMediumAccountUsername(String mediumAccountUsername)
      throws PedistackException;

  Optional<SocialMediaAccountEntity> findByLinkedInAccountUrl(String linkedInAccountUrl)
      throws PedistackException;
}
