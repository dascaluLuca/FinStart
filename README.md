FinStart — Financial Literacy for Every Romanian

"Educația financiară nu e pentru bogați. E pentru toți."


DESPRE PROIECT

FinStart este o aplicație Android educațională gratuită care îi învață pe tinerii români cum funcționează banii, inflația și investițiile — pornind de la zero cunoștințe anterioare.

Aplicația a fost dezvoltată ca răspuns la o problemă documentată și gravă: România are cea mai scăzută rată de alfabetizare financiară din Uniunea Europeană — doar 22% din adulți sunt considerați financiar alfabetizați, față de o medie UE de 52% (S&P Global FinLit Survey).


PROBLEMA PE CARE O REZOLVĂ

Resursele de educație financiară existente au limitări clare:

| Resursă existentă | Limitare |
|---|---|
| Videouri YouTube | Fragmentate, fără structură progresivă, greu de urmărit constant |
| Cursuri online | Costisitoare, lungi, descurajante pentru un începător |
| Cărți de specialitate | Limbaj tehnic, neadaptate contextului românesc |
| Reddit și forumuri | Informație dispersă, nestructurată, presupun cunoștințe anterioare |
| Aplicații internaționale | Nu acoperă specificul pieței românești: BVB, TEZAUR, FIDELIS, brokeri locali |

FinStart rezolvă toate aceste probleme simultan. Conținutul este structurat progresiv, de la concepte de bază la investiții practice, adaptat explicit contextului românesc. Aplicația funcționează complet offline, este gratuită, fără reclame și fără cont necesar. Formatul scurt și prietenos — un articol pe zi — elimină bariera psihologică a unui curs lung și intimidant. Quizul cu feedback imediat după fiecare articol transformă învățarea din pasivă în activă.


FUNCȚIONALITĂȚI

Home Screen: citat motivațional random la fiecare deschidere și articolul zilei cu sumar și buton de citire rapidă.

Bibliotecă: 15 articole organizate pe 5 categorii — Investiții, Economie, Buget personal, Psihologia banilor, Piața românească — cu navigare intuitivă spre articolul complet.

Reader articol: layout optimizat pentru citit pe mobil, line spacing mărit pentru confort vizual, buton de quiz disponibil după fiecare articol.

Quiz interactiv: 3 întrebări cu 4 variante de răspuns per articol, feedback vizual imediat, explicație detaliată pentru fiecare răspuns inclusiv cele greșite, scor final cu mesaj personalizat și progress bar pe durata quizului.

Onboarding: ecran de întâmpinare la prima deschidere care prezintă statistica-cheie și misiunea aplicației, apare o singură dată.


ARHITECTURA APLICAȚIEI

Aplicația urmează arhitectura standard Android bazată pe Activities și Fragments.

Nivelul de prezentare conține patru Activities: OnboardingActivity pentru prima deschidere cu logică de skip, MainActivity ca și container principal cu Bottom Navigation Bar, ArticleDetailActivity pentru reader și butonul de quiz, și QuizActivity pentru quizul interactiv cu feedback. MainActivity găzduiește două Fragments: HomeFragment pentru quote și articolul featured, și LibraryFragment pentru lista completă de articole.

Nivelul de date conține clasele Article și Question ca modele de date, DataManager ca singleton care citește și servește datele din JSON, și ArticleAdapter ca RecyclerView adapter pentru randarea listei.

Tot conținutul — articole și întrebări — este stocat local în fișierul articles.json din res/raw, fără nicio dependență de internet sau server extern.

Fluxul datelor pornește din articles.json, este parsat de DataManager prin librăria Gson, rezultând liste de obiecte Article și Question în memorie, care sunt servite fragmentelor și activity-urilor prin Intent cu extras.


TEHNOLOGII FOLOSITE

| Tehnologie | Versiune | Justificare |
|---|---|---|
| Java | 11 | Limbaj standard Android, robustețe, documentație vastă |
| Android Studio | Ladybug | IDE oficial Google pentru Android |
| Material Design 3 | 1.11.0 | Componente UI moderne, consistente și accesibile |
| RecyclerView | 1.3.2 | Performanță optimă pentru liste prin reciclarea view-urilor |
| Gson | 2.10.1 | Parsare JSON simplă și robustă, fără overhead |
| CardView | 1.0.0 | Prezentare vizuală clară a conținutului |
| SharedPreferences | built-in | Stocare locală pentru starea onboarding-ului |
| Git și GitHub | — | Versionare cod, istoric complet al dezvoltării |

De ce Java și nu Kotlin sau Flutter? Java oferă cel mai larg suport în documentația Android, comunitate vastă de resurse pentru debugging și compatibilitate maximă. Pentru un proiect educațional cu cerințe clare, Java reprezintă alegerea optimă între accesibilitate și putere.

De ce stocare locală și nu backend? Stocarea locală elimină orice dependență de internet, făcând aplicația funcțională în orice condiții. Nu există costuri de infrastructură, nu sunt colectate date personale — privacy by design — și timpul de răspuns este instant fără nicio latență de rețea.


INSTALARE ȘI RULARE

Cerințe: Android Studio Ladybug sau mai recent, JDK 11+, Android SDK API 24+, un dispozitiv Android sau emulator.

Varianta 1 — APK direct: descarcă finstart.apk din secțiunea Releases a repository-ului, activează "Instalare din surse necunoscute" în setările telefonului, deschide fișierul APK și instalează.

Varianta 2 — Din surse: clonează repository-ul cu git clone, deschide folderul în Android Studio, așteaptă sincronizarea Gradle (prima rulare poate dura 2-3 minute), apoi apasă Run sau Shift+F10 și selectează un emulator sau dispozitiv conectat.

Compatibilitate: minimum Android 7.0 (API 24), target Android 14 (API 34), acoperind aproximativ 95% din dispozitivele Android active.


STRUCTURA CONȚINUTULUI

| Categorie | Articole | Exemple teme |
|---|---|---|
| Investiții | 7 | ETF-uri, brokeri, diversificare, DCA, BVB, impozitare |
| Economie | 3 | Inflație, dobândă compusă, crize economice |
| Buget personal | 3 | Regula 50/30/20, fond de urgență, obiective SMART |
| Psihologia banilor | 1 | Bias-uri cognitive, panic selling, FOMO |
| Piața românească | 1 | Titluri de stat TEZAUR și FIDELIS |

Fiecare articol conține un titlu, un sumar pentru preview în bibliotecă, conținut complet formatat pentru mobil, categoria din care face parte și un set de 3 întrebări de quiz cu variante de răspuns, răspuns corect și explicație detaliată.

DEZVOLTARE VIITOARE

Quiz personalizat cu sesiuni de recapitulare pe articolele cu scor slab. Progres tracking pentru urmărirea articolelor citite și a scorurilor la quiz. Simulator de portofoliu cu investiții virtuale și date reale de piață. Notificări zilnice ca reminder pentru articolul zilei. Suport multilingv în engleză și maghiară pentru minorități. Modul admin pentru adăugarea și editarea articolelor direct din aplicație. Model freemium la 4.99 EUR pe an pentru conținut premium și calculatoare financiare.


AUTOR

Dascălu Luca-Petru
Elev, Colegiul National de Informatică "Tudor Vianu", București
