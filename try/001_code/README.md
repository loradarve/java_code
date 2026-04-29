## Git邮箱配置记录
PS D:\java_code> git config --global user.name "loradarve"
PS D:\java_code> git config --global user.email "144211810+loradarve@users.noreply.github.com"

PS D:\java_code> git config --global --list      
user.name=loradarve
user.email=144211810+loradarve@users.noreply.github.com

### 设置代理端口帮助git找到vpn
PS D:\java_code> git config --global http.proxy http://127.0.0.1:7890
PS D:\java_code> git config --global https.proxy https://127.0.0.1:7890