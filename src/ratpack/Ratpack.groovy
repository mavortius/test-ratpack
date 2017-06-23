import groovy.json.JsonSlurper
import learning.app.DefaultUserService
import learning.app.User
import learning.app.UserService

import static groovy.json.JsonOutput.toJson
import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        bindInstance UserService, new DefaultUserService()
        bindInstance JsonSlurper, new JsonSlurper()
    }
    handlers {
        path("api") { JsonSlurper jsonSlurper, UserService userService ->
            byMethod {
                post {
                    request.body.map { body ->
                        jsonSlurper.parseText(body.text) as Map
                    }.map { data ->
                        new User(data)
                    }.flatMap { user ->
                        userService.save(user)
                    }.then { user ->
                        response.send()
                    }
                }
                get {
                    userService.getUsers().then { users ->
                        response.send(toJson(users))
                    }
                }
            }
        }
    }
}
