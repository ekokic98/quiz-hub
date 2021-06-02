import { configure, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import Register from "../pages/Register";

configure({adapter: new Adapter()});

it("Renders without crashing", () => {
    shallow(<Register/>);
});

test("Registration form should have 6 fields", () => {
    const component = shallow(<Register/>);
    expect(component.find('[name="firstName"]')).toHaveLength(1);
    expect(component.find('[name="lastName"]')).toHaveLength(1);
    expect(component.find('[name="email"]')).toHaveLength(1);
    expect(component.find('[name="username"]')).toHaveLength(1);
    expect(component.find('[name="password"]')).toHaveLength(1);
    expect(component.find('[name="confirm"]')).toHaveLength(1);
});
