# gauge-mobile-enum-element
Gauge mobile enum element example

- Gauge üzerinde IOS + Android testlerinin birlikte yazıldığı mobil projelerdeki element sorununa enum ile çözüm önerisi
- Özellikle Page Object yapısı kullanılmayan, steplerini tek spec dosyası üzerinde tutan projeler için hazırlanmıştır !

Hatırlatma : 
* Uygulamayı app klasörü içerisinden yükleyebilirsiniz. Yüklerken adb install -t komutunu kullanmanız gerekiyor.
* Proje dizinine log4j logları için logs klasörünü oluşturabilirsiniz.
* Constantlar isimlendirilirken şimdilik unique olmasına dikkat edilmeli.
(Örn : Login sayfasındaki username inputu için 'L_USERNAME' benzeri bir isimlendirme yapılabilir)
      
