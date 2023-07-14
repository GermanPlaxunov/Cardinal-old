from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt

# Create your views here.
@csrf_exempt
def check(request):
    message = request.GET['message']
    username = request.GET['username']
    response = f"{username} send message: {message}"
    return HttpResponse(response)