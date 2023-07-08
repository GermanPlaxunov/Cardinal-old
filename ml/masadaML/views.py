from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt

# Create your views here.
@csrf_exempt
def check(request):
    message = request.GET['message']
    print(f"Message is: {message}")
    return HttpResponse(f"Received message: {message}")