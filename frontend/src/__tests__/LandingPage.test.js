import { configure, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import LandingPage from "../pages/LandingPage";

configure({adapter: new Adapter()});

it("Renders without crashing", () => {
    shallow(<LandingPage/>);
});

test("Landing page should have main container", () => {
    const component = shallow(<LandingPage/>);
    expect(component.find('.landing-page-container')).toHaveLength(1);
});
