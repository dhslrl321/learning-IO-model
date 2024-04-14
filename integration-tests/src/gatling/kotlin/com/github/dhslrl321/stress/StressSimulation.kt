package com.github.dhslrl321.stress

// required for Gatling core structure DSL

// required for Gatling HTTP DSL

// can be omitted if you don't use jdbcFeeder

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import io.gatling.javaapi.http.HttpProtocolBuilder

class StressSimulation : Simulation() {

    private var httpProtocol: HttpProtocolBuilder = http.baseUrl("http://localhost:8080")
            .acceptHeader("text/plain")
            .contentTypeHeader("text/plain")

    private var myFirstScenario = scenario("My First Scenario")
            .exec(
                    http("http 요청")["/"]
                            .check(status().`is`(200))
            )

    init {
        setUp(
                myFirstScenario.injectOpen(atOnceUsers(1000))
//                myFirstScenario.injectOpen(constantUsersPerSec(100.0).during(30))
        ).protocols(httpProtocol);
    }

}