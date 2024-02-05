## Localhosing

> Tunneling Service, Host your local project on public domain easily

### How to try

- 실행에 필요한 Files
```
localhosting-client/executable 내부의 
- localhosting.exe
- server.conf/localhosting.yml
```

- Command
```cmd
localhosting.exe -p #PORT -name #DOMAIN_NAME

#PORT : 로컬에서 실행 중인 프로세스 port
#DOMAIN_NAME : 부여 받을 subdomain의 name (ex. -name hello -> hello.localhosting.do-main.site가 할당)
```
