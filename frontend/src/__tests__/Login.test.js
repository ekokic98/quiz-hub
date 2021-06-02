import { configure, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import Login from "../pages/Login";

configure({adapter: new Adapter()});

it("Renders without crashing", () => {
    shallow(<Login/>);
});

test("Login form should have username and password field", () => {
    const component = shallow(<Login/>);
    expect(component.find('[name="username"]')).toHaveLength(1);
    expect(component.find('[name="password"]')).toHaveLength(1);
});
