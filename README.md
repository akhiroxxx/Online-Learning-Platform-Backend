"# Online-Learning-Platform-Backend" 

The idea behind this project is to create backend for an online learning platform. Through this students can signup and create useernames and password for themselves(and also other student details which are asked for)

Folowing this they can login, after whihc they would enter the home-screen.

On trying to route to /home/courses; all the provided courses by the platform will be shown to the user.
Subsequently clicking on a particular courseName, the api (say) /home/courses/courseName will be called and the specific details for that particular course would be provided to the user.

A user can also register and deregister for a particular course. This can be done using /home/courses/courseName/register (or) deregister.

Chatting features cam also be included between students and instructors.
Students can send messages and discuss according to the notifications sent by the instructor for every particular registered course.

The only pages which are open to all without authentication are /home , /home/courses , /login , /signup.

An instructor also has similar features but can instead send notifications rather than messages which have a better impact(probably when frontend is made its significance can be understood better), Though it is same as Message onlly. However sender is an instructor and hence it has value.

For a course to be added or an instructor to be added, the signed in user must have ADMIN role. And when an ADMIN signs in, he has /admin/addCourse , /admin/addInstructor accessible to him. And only he can make changes to course database and instructor database.

Java's Frameword:- Spring Boot is used as the backend framework
The database to store is MongoDB.

The UML Class Diagram and API Flowcharts can be found at: https://github.com/akhiroxxx/Online-Learning-Platform-Backend/tree/0e1af0ebd66a63b4bc0bee4947404c4a1664e1a9/src/main/java/com/akhilesh/Online_Learning_Project/Asset
