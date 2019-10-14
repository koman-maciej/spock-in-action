package com.oracle.trs

import com.oracle.trs.model.TrsModel
import com.oracle.trs.model.TrsRepository
import com.oracle.trs.service.TrsService
import spock.lang.Specification
import spock.lang.Unroll

class TrsServiceSpec extends Specification {

    private TrsRepository trsRepository = Mock(TrsRepository)
    private TrsService trsService

    def setup() {
        trsService = new TrsService(trsRepository)
    }

    def 'Should save entity'() {
        given:
        def newTrs = new TrsModel('title', 'desc', 'email')

        when:
        def result = trsService.insertOrUpdate(newTrs)

        then:
        1 * trsRepository.save(newTrs) >> { TrsModel model -> model }     //- mozemy nawet wiecej (dac warunek)
        result == newTrs
    }

    @Unroll
    def 'Should find entity #id'() {
        given:
        def trs = new TrsModel('title', 'desc', 'email')

        when:
        def result = trsService.find('title')

        then:
        1 * trsRepository.findById('title') >> Optional.ofNullable(trs)

        and:
        result == trs

        where:
        id << ['title', 'no title']
    }

    def 'Should throw exception when no entity found'() {
        given:
        def trs = null

        when:
        trsService.find('title')

        then:
        1 * trsRepository.findById('title') >> Optional.ofNullable(trs)

        and:
        def ex = thrown(RuntimeException)
        ex.message == 'Not found'
    }

    def 'Should find all entities'() {
        when:
        def result = trsService.findAll()

        then:
        1 * trsRepository.findAll() >> trsList

        and:
        result == trsList

        where:
        trsList << [
                [],
                [new TrsModel('aa', 'bb', 'cc')],
                [new TrsModel('aa', 'bb', 'cc'), new TrsModel('aa', 'bb', 'cc')]
        ]
    }

    def 'Should delete if it was found'() {
        given:
        def optionalTrs = Optional.ofNullable(new TrsModel('title', 'desc', 'email'))

        when:
        trsService.delete('title')

        then:
        1 * trsRepository.findById('title') >> optionalTrs
        1 * trsRepository.delete(optionalTrs.get())
    }

    def 'Should do nothing if it was not found'() {
        given:
        def optionalTrs = Optional.ofNullable(null)

        when:
        trsService.delete('title')

        then:
        1 * trsRepository.findById('title') >> optionalTrs
        0 * trsRepository.delete(_)
    }

}
