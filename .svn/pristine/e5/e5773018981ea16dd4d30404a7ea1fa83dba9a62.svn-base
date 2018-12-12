package com.gildata.byinterserver.service;

import com.gildata.byinterserver.domain.UsrBYZXXQ;
import com.gildata.byinterserver.repository.UsrBYZXXQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenchen on 2018/11/27.
 */
@Service
public class UsrBYZXXQServiceImpl implements UsrBYZXXQService{

    @Autowired
    private UsrBYZXXQRepository usrBYZXXQRepository;

    @Override
    public void save(List<UsrBYZXXQ> usrBYZXXQList) {
        usrBYZXXQRepository.saveAll(usrBYZXXQList);
    }
}
