from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
from masadaML.processor.ProcessStarter import start_analyze


# Create your views here.
@csrf_exempt
def check(request):
    message = request.GET['message']
    username = request.GET['username']
    response = f"{username} send message: {message}"
    return HttpResponse(response)


@csrf_exempt
def process_price_history(request):
    from_date = request.GET['fromDate']
    to_date = request.GET['toDate']
    symbol = request.GET['symbol']
    start_analyze(symbol=symbol,
                  from_date=from_date,
                  to_date=to_date)
