import { post } from "./common";

const personsUrl = "/persons";

export const followPerson = async (body) => {
    return await post(personsUrl + "/follow", body);
}
export const unfollowPerson = async (body) => {
    return await post(personsUrl + "/unfollow", body);
}
