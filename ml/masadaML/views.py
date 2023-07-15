from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
from masadaML.processor.ProcessStarter import start_analyze


# Create your views here.
@csrf_exempt
def analyze_price_history(request):
    from_date = request.GET['dateFrom']
    to_date = request.GET['dateTo']
    symbol = request.GET['symbol']
    print(f"request: {from_date} -> {to_date} -> {symbol}")
    result = start_analyze(symbol=symbol,
                           from_date=from_date,
                           to_date=to_date)
    return HttpResponse(f'result->{result}')
