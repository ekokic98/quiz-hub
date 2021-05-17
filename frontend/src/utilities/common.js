/*import { getToken } from "utilities/localStorage";
import { decode } from "jsonwebtoken"; */

export const validToken = () => {
    return true; // TODO, remove this after implementing token logic
   /* const token = getToken();
    if (token === null)
        return false;
    const exp = decode(token, { complete: true }).payload.exp;
    return Date.now() < exp * 1000; */
}
