import random

from locust import HttpUser, task, between

class VoteApp(HttpUser):
    wait_time = between(1, 5)

    @task
    def voting(self):
        for election in self.client.get('/api/voting').json():
            election_id = election['id']
            candidate_id = random.choices(election['candidates'])

            self.client.post(f'/api/voting/elections/{election_id}/candidates/{candidate_id}')