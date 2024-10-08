package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NextOfKinEntityDao extends BaseDao<NextOfKinEntity> {

    List<NextOfKinEntity> findByUser_Id(String userIdentifier);

}
