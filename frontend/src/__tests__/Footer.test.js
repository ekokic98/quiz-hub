import { configure, shallow } from "enzyme";
import Footer from "../shared/Footer";
import Adapter from "enzyme-adapter-react-16";

configure({adapter: new Adapter()});

it("Renders without crashing", () => {
    shallow(<Footer/>);
});

test("Footer and header are correctly loaded", () => {
    const componentFooter = shallow(<Footer/>);
    expect(componentFooter.find('.footer-container')).toHaveLength(1);
});
