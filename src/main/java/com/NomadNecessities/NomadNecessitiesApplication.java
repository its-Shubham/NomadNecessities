package com.NomadNecessities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NomadNecessitiesApplication {
	private static Logger logger = LoggerFactory.getLogger(NomadNecessitiesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NomadNecessitiesApplication.class, args);
		logger.info("====================================================================================");
		logger.info("|                                                                                  |");
		logger.info("|   N   N   OOO    M     M    A      DDDD           N   N   EEEEE   CCCC    SSSS   |");
		logger.info("|   NN  N  O   O   MM   MM   A A     D   D          NN  N   E       C       S      |");
		logger.info("|   N N N  O   O   M M M M  AAAAA    D   D          N N N   EEEE    C       SSSS   |");
		logger.info("|   N  NN  O   O   M   M M  A   A    D   D          N  NN   E       C           s  |");
		logger.info("|   N   N   OOO    M   M M  A   A    DDDD           N   N   EEEEE   CCCC    SSSS   |");
		logger.info("|                                                                                  |");
		logger.info("|               ⭐️  NOMAD NECESSITIES STARTED  ⭐️                                 |");
		logger.info("|                                                                                  |");
		logger.info("====================================================================================");
	}

}
