from v2.data.provider.QueryResultProvider import provide_all_data
from v2.data.query.TableColumnsProvider import get_core_stocks_column_names
from v2.plot.PlotBuilder import build_plot

if __name__ == '__main__':
    symbol = "BTC/USD"
    item = "stocks"
    data = provide_all_data(symbol=symbol, data_item=item)
    data.columns = get_core_stocks_column_names()
    x_list = []
    y_list = []
    x_list.append(data['date'])
    y_list.append(data['close'])
    build_plot('stock price', 'date', 'close', x_list, y_list, ['close'])
