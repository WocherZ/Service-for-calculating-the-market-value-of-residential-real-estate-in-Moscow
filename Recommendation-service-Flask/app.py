from flask import Flask, request

import json
import pandas as pd
import numpy as np
from scipy.spatial.distance import euclidean
import os

app = Flask(__name__)

df = pd.read_csv(os.getcwd() + '/static/processed.csv', sep=',')
df = df.drop(["Unnamed: 0"], axis=1)


def same(flat_dict, all_flats):
    all_flats = all_flats.dropna()
    all_flats1 = all_flats.drop(['price', 'location', 'condition'], axis=1) #!!!!!!!!!

    flat = pd.DataFrame([flat_dict])

    table = all_flats.loc[(all_flats['rooms'] == flat['rooms'][0])].reset_index(drop=True)
    table1 = all_flats1.loc[(all_flats1['rooms'] == flat['rooms'][0])].reset_index(drop=True)
    big_table = pd.concat([table1, flat]).reset_index(drop=True)
    big_table_dummies = pd.get_dummies(big_table)
    print(big_table_dummies.isnull().sum())
    distances = []
    flat_vector = big_table_dummies.tail(1).values
    print(flat_vector[0])
    for i in range(table1.shape[0]):
        possible_same_flat = big_table_dummies.loc[i, :].values
        print(len(flat_vector[0]))
        print(np.isnan(flat_vector[0]).sum())
        print(len(possible_same_flat))
        print(np.isnan(possible_same_flat).sum())

        dist = euclidean(flat_vector[0], possible_same_flat)
        distances.append(dist)
    table1['distance_to_flat'] = pd.Series(distances)
    return table.loc[table1.sort_values(by='distance_to_flat').head().index]


@app.route('/')
def hello_world():  # put application's code here
    flat = dict()
    flat['rooms'] = int(request.args['rooms'])
    flat['category'] = request.args['category']
    flat['floors'] = int(request.args['floors'])
    flat['walls_material'] = request.args['walls_material']
    flat['floor'] = int(request.args['floor'])
    flat['total_area'] = float(request.args['total_area'])
    flat['kitchen_area'] = float(request.args['kitchen_area'])
    flat['is_balcony'] = bool(True if request.args['is_balcony'] == "Да" else False)
    flat['metro_distance'] = int(request.args['metro_distance'])
    print(flat)

    same_df = same(flat, df)
    print(same_df)
    json_same = same_df.to_json(orient='records')
    json_same = json.loads(json_same)
    print(json_same)
    return json_same


if __name__ == '__main__':
    app.run(debug=True)
