from v2.data.database.connection.DatabaseConnectionFactory import get_connection_cursor
import v2.data.query.EntireDataQueryProvider as qp
import pandas as pd


def provide_all_data(symbol, data_item):
    cursor = get_connection_cursor()
    query = ''
    if data_item == 'stocks':
        query = qp.get_all_core_stocks_query(symbol)
    if data_item == 'apo':
        query = qp.get_all_apo_query(symbol)
    if data_item == 'bband':
        query = qp.get_all_bband_query(symbol)
    if data_item == 'ema':
        query = qp.get_all_ema_query(symbol)
    if data_item == 'rsi':
        query = qp.get_all_rsi_query(symbol)
    if data_item == 'sma':
        query = qp.get_all_sma_query(symbol)
    if data_item == 'std':
        query = qp.get_all_std_query(symbol)

    cursor.execute(query)
    data = cursor.fetchall()
    dataframe = pd.DataFrame(data=data)
    return dataframe
