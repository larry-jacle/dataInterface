package com.gildata.byinterserver;


import com.gildata.byinterserver.service.LoadDataService;
import com.gildata.byinterserver.service.WriteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by LiChao on 2018/11/20
 */
@Component
public class StartUpRunner implements CommandLineRunner{

    @Autowired
    private LoadDataService loadDataService;

    @Autowired
    private WriteDataService writeDataService;

    @Override
    public void run(String... strings) throws Exception {
        loadDataService.init();
        writeDataService.init();
    }
}
