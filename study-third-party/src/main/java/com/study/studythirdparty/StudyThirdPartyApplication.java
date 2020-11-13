package com.study.studythirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = {
        "com.study.studythirdparty.config*",
        "com.study.studythirdparty.controller"
})
public class StudyThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyThirdPartyApplication.class, args);
    }

}
