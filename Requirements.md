# Summary #

Our project is about creating a social network for people in Bogazici University CmpE department. It will be a closed system in which people can create their own profile pages and can share their interest topics. Users will also be able to create interest groups and events. There will be an android application and a website for this social network project.


# Background #

The application will be only for people who are somehow related to Bogazici University Computer Engineering Department. Users will register into the system with their roles in the department which are undergraduate, graduate, alumni, faculty member, teaching assistant, research assistant and support staff.

The main goal is to enable members to contact each other in an easier and faster way. Therefore, there will be profile pages of members, events, interest groups, content creating and sharing and such other properties.

Privacy will be important. A member will be able to follow another member if she/he allows. Almost any item will have option for privacy. People will choose to whom they want to share.

# Glossary #

**User:** Anyone who enters the website or uses the android application.

**Group:** A community of poeple who share a common interest.

**Member:** The user who registered into the system with his role in the department.

**Event:** A real-life event like an upcoming marathon or a meeting.

**Content:** A picture, a book, a film, an article... Any such thing which can be
specified in the system.

**Tagging:** Some keywords for the shared items for others to be able to find the item.

**Notification:** A service to notice members about what's happening.

**Administrator:** Person who manages the system, takes care of problems occur in system with his/her high access permition to the system content.

**Moderator:** Person who manages an interest group or an event independent of any group, and takes care of problems occured in that group / event.

<img src='http://intland.com/wp-content/uploads/2014/01/why-to-use-a-requirements-management-tool-for-software-development.png' align='center' width='800' height='350' />

> # Requirements #

**1.Functional Requirements
  * 1.1   Users
    * 1.1.1**         New user shall sign up by providing their user name, email address and password to the system.
      * **1.1.1.1** User shall be able to request system to reset his/her password.
      * **1.1.1.2** User shall be able to change his/her password.
    * **1.1.2**         User shall log in to the system by using their user name and password
    * **1.1.3**         User  shall have a profile page that she/he can share his or her personal information, interests etc.
    * **1.1.4**         User shall be able to send a request and receive an approval from another user if s/he wants to reach that user’s profile page.
    * **1.1.5**         User shall be able to approve or reject a request from another user.
    * **1.1.6**        User shall be able to open an interest page about a topic that s/he is interested in.
      * **1.1.6.1** User shall be able to invite other users to an group if s/he is a member of it.
      * **1.1.6.2** User shall be able to set privacy of the group s/he created, or if s/he is a moderator of that group.
      * **1.1.6.3** User shall be able to delete a group s/he created.
    * **1.1.7**  Users shall be able to supply semantic relations.
    * **1.1.8** User shall create an event.
      * **1.1.8.1** User shall be able to invite other users to an event.
      * **1.1.8.2** User shall be able to set privacy of the event s/he created, or if s/he is a moderator of that event.
      * **1.1.8.3** User shall be able to delete an event s/he created.
    * **1.1.9** A group member shall be able to share a content in that group like a photo, an article, a link etc.
      * **1.1.9.1** User shall be able to edit content that s/he had shared.
      * **1.1.9.1** Group moderator shall be able to edit or delete any content in the group.
    * **1.1.10** A group member shall comment about a content shared in that group.
    * **1.1.11** User shall be able to report a content if s/he finds that content inappropriate/disturbing.
      * **1.1.11.1** Reports shall be received by the administrator of the system.
      * **1.1.11.2** System administrator shall be able to kick any person out of the system either s/he is reported or not.
      * **1.1.11.3** Group/event moderator shall be able to kick any person out of the group either s/he is reported or not.
    * **1.1.12** User shall be able to unsubscribe from notifications from a group or event if s/he wishes.
    * **1.1.13** User shall tag the content s/he share.
    * **1.1.14** User shall be able to search content by typing tags.
      * **1.1.14.1** User shall be able to use an already existing tag or can create a new tag.
      * **1.1.14.2** User shall be able to add a tag to an existing content if it is permitted, but not anonymously.
    * **1.1.15** Users shall be able to provide and edit textual information about a group.
    * **1.1.16** Users shall be able to provide description of an event.
    * **1.1.17** Users shall be specify date and place of an event.
    * **1.1.18** Users shall be able to provide and edit textual information about a group.
    * **1.1.20** Users shall be able to set the privacy options of a group
    * **1.1.21** Users shall be able to comment to events
      * **1.1.22** User shall be able to remove someone from his or her friends list.
      * **1.1.23** User shall be able to report an interest grouo if s/he finds that interest group offensive.
      * **1.1.24** User shall be able to report an event if s/he finds that content offensive.
      * **1.1.25** User shall be able to leave a group or an event.
  * **1.2   Groups**
    * **1.2.1**  Groups shall allow users to post contents.
    * **1.2.2**  Groups shall send notifications to its members, when there is a new content available.
      * **1.2.3**  Groups shall have privacy settings section.
        * **1.2.3.1** Groups shall have option to be visible only to group members.
        * **1.2.3.2** Groups shall have option to be only reachable but not visible to outsiders.
      * **1.2.4** Requests for a group are sent to the administrator of the group.
  * **1.3   Events**
    * **1.3.1**	Events shall show which users are participating to event.
    * **1.3.2**	Events shall send notifications to participating users, when there is an update to event
  * **1.4   Topics**
    * **1.4.1** Topics shall be any item which are shared by users.
    * **1.4.2** Topics in a group shall be related to the interest subject.
  * **1.5   Searching and Tagging**
    * **1.5.1** Searching and tagging system shall provide content as many as possible.
    * **1.5.2** Searching and tagging system shall provide semantic tagging.
    * **1.5.3** Searching and tagging system shall provide semantic search.
      * **1.5.3.1** Semantic search shall also return content with semantically related tags.
  * **1.6   Notifications**
    * **1.6.1**	Notifications shall contain short description about its source.
    * **1.6.2**	Notifications shall contain an information about, why it has been created.
  * **1.7   Feedback**
    * **1.7.1**    End-user feedback shall be concerned by the project team to see needs and fallacies.
**2.  Non- Functional Requirements
  * 2.1 Availability**
    * **2.1.1** Maximum time to recover in case of failure will be 12 hours.
  * **2.2 Security and Reliability**
    * **2.2.1** System shall consistently perform according to Feature Test, Load Test and Regression Test.
    * **2.2.2** Technical errors shall be handled via "Reliability estimation based on failure-free working" rules.
  * **2.3 Usability**
    * **2.3.1** System shall succeed on usability metrics like "Completion Rates", "Task Time", "Page view/Clicks" and "Single Usability Metric(SUM)".
    * **2.3.2** System shall be efficient to use.
    * **2.3.3** System response time shall be at most 1 second.
    * **2.3.4** Design of the system shall be user friendly, it shall be of quality consistent with ISO 9126-1 and ISO 9241-11.
