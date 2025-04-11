# Rocnikovka
Projekt je rozdělen na dva samostatné programy - Server a Klient

```mermaid
User "1" -- "*" Workspace : has access to
Workspace "1" -- "*" Case : contains
Workspace "1" -- "*" SubjectContainer : contains
Case "1" -- "*" SubjectWatchlist : contains
Case "1" -- "*" MediaContainer : contains
SubjectWatchlist "1" -- "*" Subject : contains

class User {
    +Workspace currentWorkspace
    +Case currentCase
}

class Workspace {
}

class Case {
}

class SubjectContainer {
    +boolean isPublic
    +isAccessibleTo(User user, Case currentCase)
}

class MediaContainer {
    +boolean isPublic
    +isAccessibleTo(User user, Case currentCase)
}

class SubjectWatchlist {
    +boolean isPublic
    +isAccessibleTo(User user, Case currentCase)
}

class Subject {
}
```

@startuml
User "1" -- "*" Workspace : has access to
Workspace "1" -- "*" Case : contains
Workspace "1" -- "*" SubjectContainer : contains
Case "1" -- "*" SubjectWatchlist : contains
Case "1" -- "*" MediaContainer : contains
SubjectWatchlist "1" -- "*" Subject : contains

class User {
    +Workspace currentWorkspace
    +Case currentCase
}

class Workspace {
}

class Case {
}

class SubjectContainer {
    +boolean isPublic
    +isAccessibleTo(User user, Case currentCase)
}

class MediaContainer {
    +boolean isPublic
    +isAccessibleTo(User user, Case currentCase)
}

class SubjectWatchlist {
    +boolean isPublic
    +isAccessibleTo(User user, Case currentCase)
}

class Subject {
}
@enduml
