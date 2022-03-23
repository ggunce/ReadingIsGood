
# Reading Is Good


### GEREKSİNİMLER
* Java11
* MongoDB


Kitap siparişi oluşturma uygulamasıdır.

* BookController - kitap ekleme, stok güncelleme,
* CustomerController - müşteri ekleme, müşteri siparişlerini görüntüleme,
* OrderController - sipariş oluşturma, sipariş detaylarını görüntüleme, belirli tarih aralığındaki siparişleri listeme,
* StatisticsController - sipariş istatistiklerini görüntüleme servislerini içerir.


Mongodb atlas kullanılmış olup, uri projenin application.properties dosyasında verilmiştir, dilenirse bu property güncellenerek değiştirilebilir:
`spring.data.mongodb.uri=mongodb+srv://admin:admin@readingcluster.auyop.mongodb.net/readingDB?retryWrites=true&w=majority`

Swagger url: http://localhost:8080/swagger-ui/index.html 

<img width="1440" alt="swagger" src="https://user-images.githubusercontent.com/30212327/159770815-3feea074-c0d5-4b69-9ba9-d081e058d02d.png">

Postman collection projede verilmiştir. 
Servisler authentication gerektiriyor olup mevcut db icin `user: admin / pass: welcome1` ya da `user: admin2 / pass: 1111` kullanılabilir.


### Araçlar

Spring Boot, Spring Data, Spring Web, Spring Security, MongoDB




