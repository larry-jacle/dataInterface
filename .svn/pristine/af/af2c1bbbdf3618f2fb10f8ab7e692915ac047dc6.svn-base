package com.gildata.byinterserver.service;

import com.gildata.byinterserver.domain.Logrecord;
import com.gildata.byinterserver.repository.LogrecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenchen on 2018/11/27.
 */
@Service
public class LogrecordServiceImpl implements LogrecordService {

    @Autowired
    private LogrecordRepository logRecordRepository;

    public Logrecord getLogRecord(Integer id) {
        return logRecordRepository.getOne(id);
//        return logRecordRepository.getLogRecord(id).get(0);
    }

    @Override
    public List<Logrecord> findAll() {
        return logRecordRepository.findAll();
    }

    public void save(Logrecord logRecord) {
        logRecordRepository.save(logRecord);
    }

}
