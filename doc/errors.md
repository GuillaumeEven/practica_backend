Spring propose en plus des composants des advice: RestControllerAdvice
=> dans les controllers (et services)
Les exceptions doivent etre caputrée le plus près possible du point d'origine.
Parfois ce n'est possible de rester pres de l'origine, des couches supérieures peuvent rester bloquées.
=> possibilité de gérer des exceptions

2 options:
- renvoyer les messages d'erreur aux couches supérieures;
- construire un gestionnaire d'erreurs

option 2: 
/controller/handlers/GlobalControlExceptionHandler.java

@RestControllerAdvice: pour la classe

@ExceptionHandler: pour chaque méthode

Exemple: 404 personnalisable

Creacion de una clase GeneralHandler
errorCode: int
errorMessage: str

Heredité:
- UsuarioNoEncontrado (errorCode propre, message propre)
- DireccionNoEncontrada (errorCode propre, message propre)
- ...1
Ensuite il suffit d'appeler ces classes, le constructeur est géré dans la classe mere

On a donc:
- un package handler (avec @RestControllerAdvice);
- un package exception
- dans ce repo, un folder constant/ qui contient le msg custom

Dans handler, usage de ModelMap