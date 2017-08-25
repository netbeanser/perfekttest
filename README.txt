Instruments/Technologies used:
	OS Debian Linux 7.11
	IDE NetBeans 8.2
	DB PostgreSql 9.6
	Maven 3.5 (mainly for downloading jars)
	Springframework 4.3.7
	Spring-security 4.0.2
	Spring-data 1.13
	Spring-websocket 4.3.7
	Hibernate 4.3
	Tomcat-7.0.68
	git 1.7.10
	jsp/jstl
DB structure:
	Entities: Users(id,username,password) 
						Role(id,name), 
						Book(id,author,title,img_id//FK to img table*/)
						Img(id,imgpath/*must be in /resources/img/ catalogue)
	Join Tables: user_role(user_id,role_id)
								favorites(user_id,book_id)

Due to lack of time some features haven't been implenebted:
	1.server-side bean validation
  2.Unit/Integration tests
	3.https support 
	3.Though WebSockets work fine in local Intranet, in case when there exists proxy(ies) on the path
			which "dislike" http headers like "protokol upgrade" or "CONNECT: Upgrade" 
			or "CONNECT:WebSocket" connection may not be established. That's why some kind of fallback is
			needed. Spring has an opportunity to use SockJS as a fallback.


Tested using Chrome 48 and FireFox 55.
