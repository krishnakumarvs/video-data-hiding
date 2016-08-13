##To attach database:

* Open mysql using command prompt :
```sh
mysql -u root -p
<enter your password>
create database video_data_hiding;
exit
```

* Copy the dump file (video_data_hiding.txt) to desktop

* Open command prompt and enter following commands :

```sh
cd Desktop
mysql -u root -p video_data_hiding < video_data_hiding.txt
<enter your password>
```
##Your database dump is attached!

### For any help contact [me] [krishnakumarvs]
[krishnakumarvs]: <https://krishnakumarvs.com>