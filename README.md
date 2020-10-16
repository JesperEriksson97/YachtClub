Discussion and changes made from Peer Review hand in.

View Layer:

I completely restructured my View layer. I had some formatting of Strings in my MembersDatabase (Model) which should be moved to the View,
and I also had the functions PrintCompactList and PrintVerboseList duplicated in multiple view classes. I wanted to do these changes while keeping a Low Coupling
which means I didn't want to add extra association from View to Model. My solution was to create a View super class.
This meant that View would still have to have a dependency against Member and a association against MemberController but the other classes which extended View could simply just call on super() to
access the MemberController Object in View (See class diagram for further information). Also this solution collects the different print formatting functions to one class.

MemberDatabase: 

I completely removed the "id" parameters from edit and remove member functions to avoid id referring to Objects. However, I moved the creation of Member objects to the Controller.
Although controller shouldn't supposedly be responsible for creation of objects this seemed like a good candidate if following the Information Expert pattern, since View provides all
information about the objects to be created. There is now zero calls to the Model layer which connects Member objects to key/ids.

Bugs:

I fixed a bug that would set the system into a infinite loop if you entered a character instead of a letter in the main manu
I fixed the bug where the system wouldn't let you add names with spaces.

Some bugs still remain, but I chose to focus on the architecture of the software rather than fixing formatting bugs since I don't think thats the main
purpose of this assignment.

There is a bug with the reading and loading to the .xml file. It works perfectly, however sometimes it doesn't update directly and the system is required a restart for everything
to get up to date. So for example if you remove a Member and then go to in to View Members that Member will still be presented. However, if you exit the system and restart it, the Member
will not appear. I think it has something to do with the MemberDatabase variable db not updating or that DatabaseXMLParser isn't constantly writing a new file which doesn't make the
MemberDatbase to update and re-read the file. However, these kinds of faults doesn't seem very relevant for this assignment so I chose to not prioritize fixing it.
The Persistance requirement is still met in my opinion, it's just the printing which is slacking a bit.
