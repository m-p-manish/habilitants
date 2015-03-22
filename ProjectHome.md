Outil d'analyse des données de sécurité
  * Permet de collecter les informations de sécurité
  * Persister les données
  * Extraire des datamarts
  * appliquer des méthodes d'analyse (Electre, home-made, ...)
  * Alimenter un cube mondrian et faire des rapports

Agent JOSSO pour GlassFish V2
  * Agent pour servlet Catalina (OK)
  * Agent pour servlet Faces (OK)
  * Marche pas avec mondrian (prend pas en compte Valve ou Filter)
  * L'agent est utilisé pour sécuriser les parties Web du projet ci-dessus

  * New: Prise en compte des autorisations XACML sous la forme d'un protocol (intégration du projet chopSticks)