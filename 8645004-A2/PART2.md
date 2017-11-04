##  Assignment 2 - Part 2
### John L. Carveth - 8645004
### SEG2105 - M. Garzon
  
#### E12 D) --->  See E12D.uml
#### E12 E) --->  See E12E.uml
#### E12 G) --->  See E12G.uml

#### E18 D)
Calling getScale would trigger an error, as the program will keep checking super classes for the method `getScale()`, though neither EllipticalShape nor any of its superclasses have such a method.

#### E18 E)
Dynamic Binding would be used to call `translate()` from the `Shape2D` superclass.


#### 3)
See Q3.ump for UML / Umple diagram.

#### 4) Use Case Diagram
See Q4.ump (Umple was down when wriitinig this, so wouldn't compile)

#### 5)
Functional Requirements:
- The system must authenticate users and grant them appropriate permissions.
- Professors must be able to create tournaments with a configurable set of question and number of rounds.
- Students must be able to view their grade in each course.
- Students must be able to answer questions created by the professor.
- The Professor must be able to edit the class list.

Quality Requirement:
- The program should evaluate the student's answer and return a result instantly. (Performance)
- The system should be accessable at all time, with no downtime. (Reliabiliity)
- The system can handle 100,000 users (Capacity)

Platform Requirement:
- Program MUST run on iOS 7 or later, Android M and up, and latest Google Chrome / Firefox / Edge versions.