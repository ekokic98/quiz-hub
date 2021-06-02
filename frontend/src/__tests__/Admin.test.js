import { configure, shallow } from "enzyme";
import Admin from "../pages/Admin";
import Adapter from "enzyme-adapter-react-16";

configure({adapter: new Adapter()});

it("Renders without crashing", () => {
    shallow(<Admin/>);
});

test("Admin page should not have table and button when data is not loaded", () => {
    const component = shallow(<Admin/>);
    expect(component.find('Spin')).toHaveLength(1);
    expect(component.find('Table')).toHaveLength(0);
    expect(component.find('Button')).toHaveLength(0);
});
