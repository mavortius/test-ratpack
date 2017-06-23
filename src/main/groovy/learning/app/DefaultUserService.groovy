package learning.app

import ratpack.exec.Promise

class DefaultUserService implements UserService {

    private final List<User> storage = []

    @Override
    Promise<Void> save(User user) {
        storage << user
        Promise.sync {
            null
        }
    }

    @Override
    Promise<List<User>> getUsers() {
        Promise.sync {
            storage
        }
    }
}
