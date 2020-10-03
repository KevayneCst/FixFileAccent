# FixFileAccent (Projet en cours de développement)

## Présentation du projet

Quand vous travaillez sur votre code Java avec Eclipse, et que vous vous rendez compte en travaillant avec d'autres personnes, où en récupérant du code déjà existant que vous n'avez pas le même encodage et que vous le changez (en UTF-8), *paf !* toutes vos chaînes de caractères sont cassées ! Que ce soit des chaînes utiles au code (qui figurent dans des `if` ou autres conditions), ou des chaînes qui servaient de commentaires, on se retrouve bien embêté parce que oui, changer chaque caractère "�" en l'accent qui était là avant, ça peut être très (trop) long et laborieux.

*Vous vous douterez que ça m'est déjà arrivé :wink:*

C'est là que mon programme entre en jeu. Après avoir réglé quelques paramètres de configuration, vous avez seulement à indiquer le dossier dans lequel se trouve vos fichiers .java avec les accents cassées (que j'appelle "corrompus") et le programme va automatiquement corriger chaque "�" par un vrai accent directement sur le fichier (il y a une backup qui est créée à chaque lancement de correction), et donc réparer votre code.

## Mais alors, comment ça marche ?

Globalement, voici le fonctionnement, étapes par étapes (sans rentrer trop dans les détails, le code open-source est là pour ça) :

1- Le programme lit un fichier qui lui sert de base de données (que j'appelle dans mon code "Dictionnaire"), contenant 136322 mots accentués de la langue française (*pour savoir comment j'ai obtenu ces mots, allez voir mon projet [French Words](https://github.com/KevayneCst/FrenchWords)*)

2- Le programme pour chaque fichier .java, va lire chacune des lignes du fichier et va les stocker sous forme de chaînes de caractères (on aura vérifié au préalable que le fichier en question contient au moins un "�").

3- Pour chaque ligne de chaque fichier, le programme recherche les mots contenant les caractères corrompus, et va les extraire de la ligne (voir la méthode [extractCorruptedWords](https://github.com/KevayneCst/FixFileAccent/blob/e6e8d697fbba0fb2d5bc20f830bf953be452db86/src/core/grammar/Sentence.java#L60) pour voir l'algorithme qui permet d'extraire le mot).

4- Pour chacun de ces mots, le programme va, corriger le mot corrompu (voir la méthode [matchWordWithDictionnary](https://github.com/KevayneCst/FixFileAccent/blob/master/src/core/grammar/french/French.java) pour l'algorithme qui permet de corriger les mots corrompus à partir du dictionnaire).

5- Enfin, chaque fichier où l'on a eu besoin de faire une modification, on va réecrire ces fichiers en remplaçant les mots corrompus par les mots corrigés.
