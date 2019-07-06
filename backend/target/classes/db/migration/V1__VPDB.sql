CREATE TABLE `comment` (
  `idComment` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Comment` varchar(45) NOT NULL,
  `User_idUser` int(11) NOT NULL,
  `Video_idVideo` int(11) NOT NULL,
  PRIMARY KEY (`idComment`,`User_idUser`,`Video_idVideo`),
  UNIQUE KEY `idComment_UNIQUE` (`idComment`),
  KEY `fk_Comment_User1_idx` (`User_idUser`),
  KEY `fk_Comment_Video1_idx` (`Video_idVideo`),
  CONSTRAINT `fk_Comment_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Video1` FOREIGN KEY (`Video_idVideo`) REFERENCES `video` (`idVideo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB

CREATE TABLE `tag` (
  `idTag` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`idTag`),
  UNIQUE KEY `idTag_UNIQUE` (`idTag`)
) ENGINE=InnoDB

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Privilege` bit(1) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`)
) ENGINE=InnoDB

CREATE TABLE `video` (
  `idVideo` int(11) NOT NULL AUTO_INCREMENT,
  `Link` varchar(45) NOT NULL,
  `Rating` int(11) DEFAULT NULL,
  `User_idUser` int(11) NOT NULL,
  `Title` varchar(45) NOT NULL,
  PRIMARY KEY (`idVideo`,`User_idUser`),
  UNIQUE KEY `idVideo_UNIQUE` (`idVideo`),
  KEY `fk_Video_User_idx` (`User_idUser`),
  CONSTRAINT `fk_Video_User` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB

CREATE TABLE `video_has_tag` (
  `idVideoTag` int(11) NOT NULL AUTO_INCREMENT,
  `Video_idVideo` int(11) NOT NULL,
  `Tag_idTag` int(11) NOT NULL,
  PRIMARY KEY (`idVideoTag`,`Video_idVideo`,`Tag_idTag`),
  UNIQUE KEY `idVideoTag_UNIQUE` (`idVideoTag`),
  KEY `fk_Video_has_Tag_Tag1_idx` (`Tag_idTag`),
  KEY `fk_Video_has_Tag_Video1_idx` (`Video_idVideo`),
  CONSTRAINT `fk_Video_has_Tag_Tag1` FOREIGN KEY (`Tag_idTag`) REFERENCES `tag` (`idTag`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Video_has_Tag_Video1` FOREIGN KEY (`Video_idVideo`) REFERENCES `video` (`idVideo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB