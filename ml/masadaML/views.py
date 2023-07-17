from django.views.decorators.csrf import csrf_exempt
from masadaML.processor.ProcessStarter import start_analyze


# Create your views here.
@csrf_exempt
def analyze_price_history():
    # from_date = request.GET['dateFrom']
    # to_date = request.GET['dateTo']
    # symbol = request.GET['symbol']
    from_date = '2017-01-01 00:01:00.000000'
    to_date = '2017-01-01 00:59:00.000000'
    symbol = 'BTC/USD'
    print(f"request: {from_date} -> {to_date} -> {symbol}")
    result = start_analyze(symbol=symbol,
                           from_date=from_date,
                           to_date=to_date)
    # return HttpResponse(f'result->{result}')


if __name__ == '__main__':
    analyze_price_history()