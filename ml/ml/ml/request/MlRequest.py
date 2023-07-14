class MlRequest:
    def __init__(self, username, message):
        self.username = username
        self.message = message

    def get_message(self):
        return f'{self.username} send message: {self.message}'
