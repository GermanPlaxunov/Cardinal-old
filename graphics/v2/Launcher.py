from v2.data.provider.QueryResultProvider import provide_all_data

if __name__ == '__main__':
    symbol = "BTC/USD"
    item = "stocks"
    data = provide_all_data(symbol=symbol, data_item=item)
    print(data)
