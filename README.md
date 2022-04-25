
# Allegro Summer Experience 2022

Assignment 3 - Software Engineer

My email in recruitment process: <patrykferenc@protonmail.com>

English below.

---

# Jak zacząć? Instrukcje instalacji

Aplikację można uruchomić za pomocą IDE (np. IntelliJ) z Gradle, lub poprzez archiwum jar.

## Uruchomienie za pomocą IDE (Gradle)

1. Sklonuj repozytorium do folderu.
2. Możesz uruchomić aplikację w IDE **(IntelliJ IDEA ma plugin Gradle - wystarczy użyć taska bootRun)**,
   lub manualnie za pomocą poniższej komendy:
```
$ ./gradlew bootRun
```
3. Aplikacja powinna już działać, przejdź do sekcji "Jak to działa", aby dowiedzieć się więcej.

Uwagi: Konieczna jest Java 11+ oraz Gradle 4+ 

## Uruchomienie archiwum

1. Pobierz archiwum jar (jest w sekcji RELEASES).
2. Uruchom aplikację poniższą komendą:
```
java -jar githubapi-1.0.0.jar
```
3. Aplikacja powinna już działać, przejdź do sekcji "Jak to działa", aby dowiedzieć się więcej.

Notes: Konieczna jest Java 11+

---

# Jak to działa? Instrukcja obsługi

Aplikacja komunikuje się z REST API GitHub'a i zwraca informacje językach używanych przez danego użytkownika
oraz elementy jego profilu.
Po uruchomieniu aplikacji, pod adresem podanym:
```
http://localhost:8080/stats
```
...będzie można otrzymać informacje o użytkowniku [octocat](https://github.com/octocat).

Można również sprawdzić statystyki uzytkownika o danej nazwie (w poniższym przykładzie - użytkownika 'username'):
```
http://localhost:8080/stats?name=username
```

Próba wyświetlenia informacji o użytkowniku, który nie istnieje lub przekroczenie
ilości requestów spowoduje wysłanie komunikatu o błędzie.

---

# Ograniczenia, uwagi i inne komentarze

REST API GitHub'a ma bardzo niewielki limit ilości zapytań dla użytkownika nieautoryzowanego (60/h).
Dlatego niestety łatwo jest go przekroczyć, szczególnie gdy użytkownik ma sporo repozytoriów.
Niestety informacje o każdym repo znajdują się pod innymi adresami i wymagają oddzielnych requestów.
Można by ominąć to ograniczenie (właściwie to zwiększyć limit - do 5000/h), jednak wiązałoby się to
z koniecznością podania swojego tokena, lub stworzenia oddzielnego użytkownika, który miałby taki token.
Zdecydowałem się nie implementować tego rozwiązania, ponieważ udostępnianie własnych tokenów gdziekolwiek 
jest bardzo złą praktyką.

---

# How to get started

There are two ways to run the program: either via IDE (with Gradle) or via standalone archive.

## Running via Gradle task (in IDE)

1. Clone the repository.
2. You can either run it via IDE **(IntelliJ IDEA has built in gradle plugin that lets you do this - just run with the bootRun task)**,
or you can do it manually, if you navigate to the root of the project and run:
```
$ ./gradlew bootRun
```
3. The application should be running now. Check how it works in the "How it works" section.

Notes: make sure you use Java 11+ and Gradle 4+

## Running the archived application.

1. Download the jar archive.
2. Run it via this command:
```
java -jar githubapi-1.0.0.jar
```

Notes: make sure you use Java 11+

---

# How it works

The application communicates with the GitHub REST API and returns information about user's languages 
and some profile information.
Once you start the application you can try it out by navigating to the endpoint at:
```
http://localhost:8080/stats
```
...where you should see statistics of the default GitHub user [octocat](https://github.com/octocat).

You can also see statistics about a given user (in this example the user is called 'username'):
```
http://localhost:8080/stats?name=username
```

Trying to get information about a user that does not exist, or exceeding rate limits will return a custom error.

---

# Constraints, simplifications and additional remarks

The GitHub REST API has a very low limit of requests for unauthenticated users (60/hr).
Therefore, it is relatively easy to exceed it, especially when the user has a lot of repositories, 
as every one of them has to be a unique request (info about languages is stored under a different address).
One way to fix it is by - well - authentication via a personal token, but I decided against it, 
as it is potentially a security hazard.