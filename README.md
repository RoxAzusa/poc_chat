# POC Chat - Your Car Your Way

## Introduction

Ce projet est une preuve de concept (PoC) qui vise à démontrer la faisabilité d’un système de chat en ligne, permettant aux clients et au service support de communiquer en temps réel. Ce PoC s’inscrit dans le cadre d’une nouvelle stratégie pour Your Car Your Way, une société de location de voitures présente depuis une vingtaine d’années sur le marché européen et américain. L’objectif est de regrouper l’ensemble des fonctionnalités de location et d’assistance dans une application web unifiée, remplaçant les anciennes applications spécifiques à chaque pays. La fonctionnalité de chat est conçue pour permettre une communication bidirectionnelle entre deux utilisateurs : un client (User) et un agent support (Support), afin de servir de support instantanné aux utilisateurs.

## Features

- **Authentification** : Le système propose une page de connexion pour permettre aux utilisateurs de s’identifier (login via email et mot de passe). Le backend (Spring Boot) assure l’authentification via une API dédiée.
- **Chat en temps réel** : La mise en œuvre d’un chat utilisant la technologie WebSocket avec la librairie SockJS et STOMP permet la transmission instantanée des messages. Le front Angular se connecte à l’endpoint "/ws" configuré dans le backend.
- **Historique des messages** : Chaque conversation est sauvegardée dans la base de données via JPA. Les messages sont associés à une session de chat stockée en base.
- **Déconnexion et fin de session** : Une fonctionnalité de déconnexion permet de mettre fin à la conversation. Lorsque l’utilisateur clique sur le bouton "Déconnexion", une requête est envoyée pour indiquer que le chat est terminé.

## Technologies utilisées

### Backend

- Java 21
- Spring Boot
- MySQL

### Frontend

- Angular 19
- SockJs
- STOMP
- RxJS

## Prérequis

Avant de commencer, assurez-vous d'avoir installé les outils suivants :  
### Backend

- [Java 21](https://www.oracle.com/java/technologies/downloads/#jdk21-windows)  
- [Maven](https://maven.apache.org/download.cgi)  
- [MySQL](https://dev.mysql.com/downloads/)

### Frontend

- [Node.js 18+](https://nodejs.org/en)
- [Angular CLI](https://angular.dev/tools/cli)

### Outils recommandés

- [Git](https://git-scm.com/)  
- Un IDE comme [Eclipse](https://www.eclipse.org/)
- [VS Code](https://code.visualstudio.com/)

## Installation du projet

### Étape 1 : Cloner le dépôt
Clonez le dépôt GitHub et accédez au répertoire du projet :  
```bash
https://github.com/RoxAzusa/poc_chat.git
cd poc_chat
```

### Étape 2 : Configurer les variables d'environnement (sous Windows)
Afin de sécuriser les informations sensibles, les identifiants de la base de données sont configurés comme variables d'environnement système.

1. Configurer les variables :

Définissez les variables d'environnement suivantes sur votre machine :
- Allé dans les `Paramètres` -> `Système` -> `Informations système` -> `Paramètres avancés du système`
- Dans la fenêtre `Propriétés système` allé dans l'onglet `Paramètres système avancés` puis dans `Variables d'environnement...`
- Dans la section `Variable système` ajouter cette nouvelle variable :
    ```
    spring.datasource.password=yourDatabasePassword
    ```
- Validez les modifications, puis redémarrez votre terminal ou votre système si nécessaire.

### Étape 3 : Configurer la base de données
1. Personnalisez la connexion à la base de données en modifiant le fichier `application.properties` :  
   ```properties
   # Exemple de configuration pour MySQL
    spring.datasource.url=jdbc:mysql://localhost:3306/poc_chat?allowPublicKeyRetrieval=true
    spring.datasource.username=root
    spring.datasource.password=${spring.datasource.password}
   ```

2. Créez une base de données nommée `poc_chat` :  
     ```sql
     CREATE DATABASE poc_chat;
     ```

3. Initalisez la base de données avec le `script.sql` fournit dans le dossier **ressources\sql**

4. Assurez-vous que le service MySQL est démarré et que les identifiants configurés dans `application.properties` sont corrects.

### Étape 4 : Construire et exécuter le projet

#### Côté backend :

Installez les dépendances Maven et lancez l'application :  
```bash
mvn clean install
mvn spring-boot:run
```

L'API sera accessible sur : 
```
http://localhost:8080
```

#### Côté frontend :

Se déplacer dans le dossier frontend
```
cd ../frontend
```

Installez les dépendances
```
npm install
```

Lancer le frontend
```
ng serve
```

L'interface utilisateur sera accessible sur : 
```
http://localhost:4200
```

## URL de l'application

### Accéder à l'application
Une fois l'application démarrée, elle est accessible à cette adresse : 

```
http://localhost:4200
```

## Usage

Pour tester la fonctionnalité de chat, procédez comme suit :

1. **Lancement de l’application :**
   - Démarrez le backend via Maven.
   - Lancez le serveur de développement du front avec la commande `ng serve`.
   
2. **Connexion :**
   - Ouvrez deux instances de navigateur (ou utilisez une fenêtre de navigation privée pour l’une d’elles).
   - Sur l’une des fenêtres, connectez-vous en tant qu’utilisateur "User" en utilisant les identifiants fournis dans le script SQL (par exemple, email : user@test.com, mot de passe : user).
   - Dans l’autre fenêtre, connectez-vous en tant que "Support" (par exemple, email : support@test.com, mot de passe : support).

3. **Démarrer le chat :**
   - Une fois connecté, l’utilisateur "User" envoie un premier message à travers l’interface de chat.
   - Le support reçoit le message en temps réel grâce à la connexion WebSocket et peut y répondre instantanément.
   
4. **Fin de session :**
   - Pour mettre fin à la conversation, cliquez sur le bouton "Déconnexion", ce qui enverra une requête pour clôturer la session dans le backend et redirigera vers la page de connexion.

## Auteur

- **Roxane Crépeaux**  
  [GitHub](https://github.com/RoxAzusa)
