# jpa-elearning2
 

인프런과 같은 동영상 강의 홈페이지를 구현하는데 필요한 벡엔드 서버 개발
 
 ## 개발환경
 JPA
 
 MYSQL
 
 SPRINGBOOT 
 
 JAVA 17
 
 
 
 # api 명세
 

  /user/** 형식으로 구성된 uri 는 role 이 user 여야 합니다. 따라서 로그인후 토큰을 받은뒤에 Bearer token 값에
토큰을 넣어주어야 정상적으로 동작합니다.

 회원가입(register)시에는 첫 단계이므로 토큰이 필요없습니다.
 
 # member  

 <details markdown="1">
<summary> register</summary>
 

 ## /register

![image](https://user-images.githubusercontent.com/42957005/217699539-c8b66b27-3af6-44fa-b718-7e65a640857c.png) 
</details>


 <details markdown="1">
<summary> delete</summary>
 

 ## /user/delete/{account}

 ![화면 캡처 2023-01-30 205125](https://user-images.githubusercontent.com/42957005/215470082-f5961d1e-a361-4d6a-95d7-c0dedaf0fdbc.png)
 
</details>

 

 <details markdown="1">
<summary> login</summary>
RequestBody  
 
      String account;

      String password;

      
    
 ## /login

![image](https://user-images.githubusercontent.com/42957005/215474246-5ec1e7ba-4214-43d9-a50f-04b598bdbff3.png) 
</details>



 <details markdown="1">
<summary> get all user</summary> 
 ## /user/getusers

![image](https://user-images.githubusercontent.com/42957005/215474807-a7fa38c8-7ec6-49cc-b9f9-12b05bf8e5ad.png)
</details>








 <details markdown="1">
<summary>modify user password</summary>
 

 ## /user/modify/{account}
request parameter : string password 
 
![image](https://user-images.githubusercontent.com/42957005/215475607-c8cace69-a747-454c-a7c6-549f5293060f.png)
![image](https://user-images.githubusercontent.com/42957005/215475390-2a10c67d-ef19-4c88-b231-4675d660311a.png)

</details>







 <details markdown="1">
<summary>find user by account</summary>
 

 ## /user/get
  RequestParam String account
![image](https://user-images.githubusercontent.com/42957005/221413882-38835e3b-7dbe-46f1-8b99-a8e705dcdfcb.png)
</details>
 





 <details markdown="1">
<summary>add admin authority</summary>
 

 ## /user/toadmin
  RequestParam String email
![image](https://user-images.githubusercontent.com/42957005/221413853-6a6772f1-84a4-4aee-80f1-d865cef93f8e.png)
</details>
 



 <details markdown="1">
<summary>get admin </summary>
 

 ## /user/admin/get
  RequestParam : String account
![image](https://user-images.githubusercontent.com/42957005/221413809-998f7fe1-3efe-4a9a-9eb4-d698c3cff0a1.png)
</details>




 <details markdown="1">
<summary> 포인트 추가하기 </summary>
 
![image](https://user-images.githubusercontent.com/42957005/221413725-127f4593-bde5-4b90-b8de-b3f679b823c7.png)
</details>




# board
file 은 board 정보의 일부분이다.

 <details markdown="1">
<summary> board 생성 </summary>
 
### post 방식
##  /user/save/file
  
     RequestBody  
      String text;

      String description;

      String filepath;
       Long price;

   
    
</details>

 


 <details markdown="1">
<summary> 구매한 board </summary>
 
### get 방식
## /user/boughtfiles
RequestParam String email
(사용자의 이메일) 
 
</details>


 <details markdown="1">
<summary> board 구매 </summary>
 
### post 방식
##  /user/buy/board
  
     @RequestBody  
     
     
      String email;
      String filetext;

만약 사용자의 돈이 부족하면 board 구매할수 없다
    
</details>





 <details markdown="1">
<summary> filetitle 을 keyword 을 이용해 검색 </summary>
 
### get 방식
##  /user/searchfiles
  
     @RequestParam  
   
      String searchkeyword 
   

     
</details>



 <details markdown="1">
<summary>board 삭제 </summary>
 
### delete 방식
##  /user/deleteboard
  
     @RequestParam  
   
      String title 
    
</details>





 <details markdown="1">
<summary>image 파일 보기 </summary>
 
### get 방식
##  /user/fileimg
  
     @RequestParam  
   
      String title 
      String filename
    
</details>




 <details markdown="1">
<summary>video 파일 보기 </summary>
 
### get 방식
##  /user/filevid
  
     @RequestParam  
   
      String title 
      String filename
    
</details>

