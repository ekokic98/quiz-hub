import { basicGet } from "api/common";

const personUrl = "/person/api";

export const getAllPersons = async () => {
    return await basicGet(personUrl + '/persons/all');
};
