# gauge-mobile-enum-element
Gauge mobile enum element example

- Gauge üzerinde IOS + Android testlerinin birlikte yazıldığı mobil projelerdeki element sorununa enum ile çözüm önerisi
- Özellikle Page Object yapısı kullanılmayan, steplerini tek spec dosyası üzerinde tutan projeler için hazırlanmıştır !

Hatırlatma : 
* Testlerde kullanılan mobil uygulamayı app klasöründen `adb install -t <apk name>` komutu ile yükleyebilirsiniz.
* Proje dizinine log4j logları için logs klasörünü oluşturabilirsiniz.
* Constantlar oluşturulurken name in unique olmasına dikkat edilmeli.
(Örn : Login sayfasındaki username inputu için 'L_USERNAME' benzeri bir isimlendirme yapılabilir)
      
