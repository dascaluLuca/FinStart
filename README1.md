

```markdown
# FinStart — Financial Literacy for Every Romanian

> *"Educația financiară nu e pentru bogați. E pentru toți."*

---

## 📌 Despre proiect

FinStart este o aplicație Android educațională gratuită care îi învață pe tinerii români cum funcționează banii, inflația și investițiile — pornind de la zero cunoștințe anterioare.

Aplicația a fost dezvoltată ca răspuns la o problemă documentată și gravă: **România are cea mai scăzută rată de alfabetizare financiară din Uniunea Europeană** — doar 22% din adulți sunt considerați financiar alfabetizați, față de o medie UE de 52% (S&P Global FinLit Survey).

---

## 🎯 Problema pe care o rezolvă

Resursele de educație financiară existente au limitări clare:

| Resursă existentă | Limitare |
|---|---|
| Videouri YouTube | Fragmentate, fără structură progresivă, greu de urmărit constant |
| Cursuri online | Costisitoare, lungi, descurajante pentru un începător |
| Cărți de specialitate | Limbaj tehnic, nu adaptate contextului românesc |
| Reddit / forumuri | Informație dispersă, nestructurată, presupun cunoștințe anterioare |
| Aplicații internaționale | Nu acoperă specificul pieței românești (BVB, TEZAUR, FIDELIS, brokers locali) |

**FinStart rezolvă toate aceste probleme simultan:**
- Conținut structurat progresiv, de la concepte de bază la investiții practice
- Adaptat explicit contextului românesc (BNR, ASF, XTB, TradeVille, titluri de stat)
- Funcționează complet offline — accesibil oricând, oriunde
- Format scurt și prietenos — un articol pe zi, nu ore de studiu
- Quiz cu feedback imediat după fiecare articol — învățare activă, nu pasivă
- Gratuit, fără reclame, fără cont necesar

---

## ✨ Funcționalități

### 🏠 Home Screen
- Citat motivațional random la fiecare deschidere a aplicației
- Articolul zilei cu sumar și buton de citire rapidă

### 📚 Bibliotecă
- 15 articole organizate pe 5 categorii: Investiții, Economie, Buget personal, Psihologia banilor, Piața românească
- RecyclerView cu preview titlu, categorie și sumar
- Navigare intuitivă spre articolul complet

### 📖 Reader articol
- Layout optimizat pentru citit pe mobil
- Line spacing mărit pentru confort vizual
- Buton de quiz disponibil după fiecare articol

### 🧠 Quiz interactiv
- 3 întrebări cu 4 variante de răspuns per articol
- Feedback vizual imediat (verde/roșu)
- Explicație detaliată pentru fiecare răspuns — inclusiv pentru cele greșite
- Scor final cu mesaj personalizat în funcție de performanță
- Progress bar pe durata quizului

### 👋 Onboarding
- Ecran de întâmpinare la prima deschidere
- Prezintă statistica-cheie și misiunea aplicației
- Apare o singură dată (salvat în SharedPreferences)

---

## 🏗️ Arhitectura aplicației

```
FinStart
│
├── Activities
│   ├── OnboardingActivity    ← prima deschidere, logic de skip
│   ├── MainActivity          ← container principal cu Bottom Navigation
│   ├── ArticleDetailActivity ← reader articol + buton quiz
│   └── QuizActivity          ← quiz interactiv cu feedback
│
├── Fragments
│   ├── HomeFragment          ← quote + articol featured
│   └── LibraryFragment       ← RecyclerView cu toate articolele
│
├── Data Layer
│   ├── Article.java          ← model de date pentru articol
│   ├── Question.java         ← model de date pentru întrebare quiz
│   └── DataManager.java      ← singleton, citește și servește datele din JSON
│
├── UI
│   └── ArticleAdapter.java   ← RecyclerView adapter
│
└── res/raw/
    └── articles.json         ← toate articolele și întrebările (offline)
```

### Fluxul datelor

```
articles.json (res/raw)
        ↓
DataManager.getInstance() — singleton, parsează JSON cu Gson
        ↓
List<Article> / List<Question> în memorie
        ↓
Adapter / Fragment → RecyclerView / TextView
        ↓
Intent cu extras → ArticleDetailActivity → QuizActivity
```

---

## 🛠️ Tehnologii folosite

| Tehnologie | Versiune | Justificare |
|---|---|---|
| Java | 11 | Limbaj standard Android, robustețe, documentație vastă |
| Android Studio | Ladybug | IDE oficial Google pentru Android |
| Material Design 3 | 1.11.0 | Componentele UI moderne, consistente și accesibile |
| RecyclerView | 1.3.2 | Performanță optimă pentru liste lungi prin reciclarea view-urilor |
| Gson | 2.10.1 | Parsare JSON simplă și robustă, fără overhead |
| CardView | 1.0.0 | Prezentare vizuală clară a conținutului |
| SharedPreferences | built-in | Stocare locală ușoară pentru starea onboarding-ului |
| Git + GitHub | — | Versionare cod, istoric complet al dezvoltării |

**De ce Java și nu Kotlin sau Flutter?**
Java oferă cel mai larg suport în documentația Android, comunitate vastă de resurse pentru debugging și compatibilitate maximă. Pentru un proiect educațional cu cerințe clare, Java reprezintă alegerea optimă între accesibilitate și putere.

**De ce stocare locală (JSON) și nu backend?**
- Zero dependență de internet — aplicația funcționează în orice condiții
- Fără costuri de infrastructură — sustenabilă pe termen lung
- Fără date personale colectate — privacy by design
- Timp de răspuns instant — nicio latență de rețea

---

## 📦 Instalare și rulare

### Cerințe
- Android Studio Ladybug sau mai recent
- JDK 11+
- Android SDK API 24+ (Android 7.0)
- Un dispozitiv Android sau emulator

### Pași de instalare

**Varianta 1 — APK direct**
1. Descarcă `finstart.apk` din secțiunea [Releases](../../releases)
2. Activează "Instalare din surse necunoscute" în setările telefonului
3. Deschide fișierul APK și instalează

**Varianta 2 — Din surse**
```bash
git clone https://github.com/dascalu-luca/finstart.git
cd finstart
```
1. Deschide folderul în Android Studio
2. Așteaptă sincronizarea Gradle (prima rulare poate dura 2-3 minute)
3. Apasă Run ▶️ sau `Shift + F10`
4. Selectează un emulator sau dispozitiv conectat

### Compatibilitate
- **Minimum:** Android 7.0 (API 24)
- **Target:** Android 14 (API 34)
- **Acoperire:** ~95% din dispozitivele Android active

---

## 📂 Structura conținutului

### Categorii disponibile

| Categorie | Articole | Exemple teme |
|---|---|---|
| Investiții | 7 | ETF-uri, brokeri, diversificare, DCA, BVB, impozitare |
| Economie | 3 | Inflație, dobândă compusă, crize economice |
| Buget personal | 3 | Regula 50/30/20, fond de urgență, obiective SMART |
| Psihologia banilor | 1 | Bias-uri cognitive, panic selling, FOMO |
| Piața românească | 1 | Titluri de stat TEZAUR și FIDELIS |

### Structura unui articol în JSON
```json
{
  "id": "1",
  "title": "Titlul articolului",
  "summary": "Sumar scurt pentru preview în bibliotecă",
  "content": "Conținut complet cu \n\n pentru paragrafe",
  "category": "Categoria",
  "isFeatured": true,
  "questions": [
    {
      "question": "Textul întrebării?",
      "options": ["Varianta A", "Varianta B", "Varianta C", "Varianta D"],
      "correctIndex": 0,
      "explanation": "Explicație detaliată a răspunsului corect."
    }
  ]
}
```

---

## 🌍 Obiective de Dezvoltare Durabilă (SDGs)

- **SDG 4 — Educație de calitate:** acces gratuit la educație financiară structurată
- **SDG 8 — Muncă decentă și creștere economică:** empowerment financiar individual
- **SDG 10 — Reducerea inegalităților:** democratizarea accesului la cunoaștere financiară

---

## 🔮 Dezvoltare viitoare

- **Quiz personalizat** — sesiuni de recapitulare pe articolele cu scor slab
- **Progres tracking** — urmărirea articolelor citite și a scorurilor la quiz
- **Simulator de portofoliu** — investiții virtuale cu date reale de piață
- **Notificări zilnice** — reminder pentru articolul zilei
- **Suport multilingv** — engleză și maghiară pentru minorități
- **Modul admin** — adăugare și editare articole direct din aplicație
- **Model freemium** — 4.99 EUR/an pentru conținut premium și calculatoare financiare

---

## 👨‍💻 Autor

**Dascălu Luca-Petru**
Elev, Colegiul de Informatică, București
GitHub: [@dascalu-luca](https://github.com/dascalu-luca)

---

## 📄 Licență

Proiect educațional open-source. Conținutul articolelor este original și poate fi utilizat în scop educațional cu atribuire.

---

*FinStart — Pentru că educația financiară nu ar trebui să fie un privilegiu.*
```

---
