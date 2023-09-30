from connection.ConnectionFactory import get_connection_cursor
from plot.Plotter import show
import QueryProvider as qp
import pandas as pd

if __name__ == '__main__':
    cursor = get_connection_cursor()
    query = qp.get_all_core_stocks_query()
    cursor.execute(query)
    stocks = cursor.fetchall()
    dataframe = pd.DataFrame(data=stocks)
    show(dataframe)