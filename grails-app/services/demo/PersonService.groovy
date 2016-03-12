package demo

import grails.transaction.Transactional

@Transactional
class PersonService {

    List<String> getFirstNamesForLastName(String lastName) {
//        Person.where {
//            lastName == lastName
//        }.projections {
//            property 'firstName'
//        }.list()

        Person.executeQuery 'select p.firstName from Person p where lastName = :lastName', [lastName: lastName]
    }
}
