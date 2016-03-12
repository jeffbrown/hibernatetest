package demo

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.hibernate.HibernateTestMixin
import spock.lang.Specification

@TestFor(PersonService)
@TestMixin(HibernateTestMixin)
class PersonServiceSpec extends Specification {

    void setupSpec() {
        hibernateDomain([Person])
    }

    void "test hql query"() {
        setup:
        new Person(firstName: 'Jeff', lastName: 'Brown').save()
        new Person(firstName: 'Zack', lastName: 'Brown').save()
        new Person(firstName: 'Jake', lastName: 'Brown').save()
        new Person(firstName: 'Steve', lastName: 'Morse').save()
        new Person(firstName: 'Neil', lastName: 'Morse').save()
        new Person(firstName: 'Alex', lastName: 'Lifeson').save()

        expect:
        service.getFirstNamesForLastName(lastName).size() == numberOfFirstNames

        where:
        lastName  | numberOfFirstNames
        'Brown'   | 3
        'Morse'   | 2
        'Lifeson' | 1
    }
}
