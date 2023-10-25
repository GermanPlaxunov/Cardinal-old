def get_all_core_stocks_query(symbol):
    query = 'select * from libra.core_stock where symbol = \'{}\' order by "date" asc'.format(symbol)
    return query

def get_all_apo_query(symbol):
    query = 'select * from libra.apo where symbol = \'{}\' order by "date" asc'.format(symbol)
    return query

def get_all_bband_query(symbol):
    query = 'select * from libra.bbands where symbol = \'{}\' order by "date" asc'.format(symbol)
    return query

def get_all_ema_query(symbol):
    query = 'select * from libra.ema where symbol = \'{}\' order by "date" asc'.format(symbol)
    return query

def get_all_rsi_query(symbol):
    query = 'select * from libra.rsi where symbol = \'{}\' order by "date" asc'.format(symbol)
    return query

def get_all_sma_query(symbol):
    query = 'select * from libra.sma where symbol = \'{}\' order by "date" asc'.format(symbol)
    return query

def get_all_std_query(symbol):
    query = 'select * from libra.std where symbol = \'{}\' order by "date" asc'.format(symbol)
    return query