package learning.app

import ratpack.exec.Promise

interface UserService {
    Promise<Void> save(User user)
    Promise<List<User>> getUsers()
}